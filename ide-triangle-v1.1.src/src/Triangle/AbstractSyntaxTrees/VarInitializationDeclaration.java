/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;
import javax.xml.transform.Source;

/**
 *
 * @author Fabian
 */
public class VarInitializationDeclaration extends Declaration{
    public VarInitializationDeclaration(Identifier iAST, Expression eAST, SourcePosition thePosition){
        super(thePosition);
        I = iAST;
        E = eAST;
    }
    
    public VarInitializationDeclaration (Identifier iAST, TypeDenoter tAST, SourcePosition thePosition) {
        super (thePosition);
        I = iAST;
        T = tAST;
    }

    public Object visit(Visitor v, Object o) {
        return v.visitVarInitializationDeclaration(this, o);
    }
    public Identifier I;
    public Expression E;
    public TypeDenoter T;    
}
