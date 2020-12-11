/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Fabian
 */
public class LocalDeclaration extends Declaration{
    public LocalDeclaration(Declaration d1, Declaration d2, SourcePosition thePosition){
        super(thePosition);
        this.D1 = d1;
        this.D2 = d2;
        
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitLocalDeclaration(this, o);
    }
    public Declaration D1, D2;
}
