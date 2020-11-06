/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Adrian
 */
public class Proc_Funcs  extends Proc_Func{

  public Proc_Funcs (Proc_Func ProcFunc1, Proc_Func ProcFunc2, SourcePosition thePosition) {
    super (thePosition);
    PF1 = ProcFunc1;
    PF2 = ProcFunc1;
  }

  public Object visit(Visitor v, Object o) {
    return v.visitProc_Funcs(this, o);
  }  
   
  public Proc_Func PF1, PF2;
}
