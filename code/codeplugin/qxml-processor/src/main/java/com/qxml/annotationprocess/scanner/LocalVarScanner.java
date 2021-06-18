package com.qxml.annotationprocess.scanner;

import com.qxml.tools.model.LocalVarInfoModel;
import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.Element;

public class LocalVarScanner extends TreeScanner {

    private Trees trees;
    private Map<String, LocalVarInfoModel> localVarMap = new HashMap<>();
    private VarScanner varScanner = new VarScanner(localVarMap);

    public LocalVarScanner(Trees trees) {
        this.trees = trees;
    }

    public Map<String, LocalVarInfoModel> scanAndGenerate(Set<? extends Element> localVarAnnotations) {
        for (Element element: localVarAnnotations) {
            JCTree jc = (JCTree) trees.getTree(element);
            jc.accept(this);
        }
        return localVarMap;
    }

    @Override
    public void visitVarDef(JCTree.JCVariableDecl jcVariableDecl) {
        super.visitVarDef(jcVariableDecl);
        if (!jcVariableDecl.getType().type.isPrimitive()) {
            varScanner.curParentName = jcVariableDecl.name.toString();
            JCTree jc = (JCTree) trees.getTree(jcVariableDecl.getType().type.asElement());
            jc.accept(varScanner);
        }
    }

    private static class VarScanner extends TreeScanner {

        private Map<String, LocalVarInfoModel> localVarMap;
        public String curParentName = "";

        public VarScanner(Map<String, LocalVarInfoModel> localVarMap) {
            this.localVarMap = localVarMap;
        }

        @Override
        public void visitVarDef(JCTree.JCVariableDecl jcVariableDecl) {
            String varName = curParentName + "_" + jcVariableDecl.sym.toString();
            String localVarDefInitBlock = jcVariableDecl.vartype.toString() + " " + varName + " = " + jcVariableDecl.init.toString();
            String localVarResetBlock = varName + " = " + jcVariableDecl.init.toString();

            localVarMap.put(varName, new LocalVarInfoModel(localVarDefInitBlock, localVarResetBlock, curParentName, jcVariableDecl.vartype.toString(), jcVariableDecl.sym.toString()));
        }
    }

}
