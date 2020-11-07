/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Anthony Ulloa
 */
public class ProcProc_Func extends Proc_Func {

  public ProcProc_Func (Identifier iAST, FormalParameterSequence fpsAST, Command cAST,
             SourcePosition thePosition) {
    super (thePosition);
    I = iAST;
    FPS = fpsAST;
    C = cAST;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitProcProc_Func(this, o);
  }

  public Identifier I;
  public FormalParameterSequence FPS;
  public Command C;
}