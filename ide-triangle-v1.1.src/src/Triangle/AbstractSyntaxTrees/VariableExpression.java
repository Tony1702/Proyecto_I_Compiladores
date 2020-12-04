/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

/**
 *
 * @author Tony1
 */

import Triangle.SyntacticAnalyzer.SourcePosition;

public class VariableExpression extends Expression {

  public VariableExpression (VariableLiteral vlAST, SourcePosition thePosition) {
    super (thePosition);
    VL = vlAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitVariableExpression(this, o);
  }

  public VariableLiteral VL;
}
