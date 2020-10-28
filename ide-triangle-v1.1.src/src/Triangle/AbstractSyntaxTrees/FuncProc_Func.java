/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Tony1
 */
public class FuncProc_Func extends Proc_Func {

  public FuncProc_Func (Identifier iAST, FormalParameterSequence fpsAST, TypeDenoter tAST, Expression eAST,
             SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    FPS = fpsAST;
    T = tAST;
    E = eAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitFuncProc_Func(this, o);
  }

  public Identifier I;
  public FormalParameterSequence FPS;
  public TypeDenoter T;
  public Expression E;
}