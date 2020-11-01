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
public abstract class Proc_Func extends AST {

  public Proc_Func (SourcePosition thePosition) {
    super (thePosition);
    duplicated = false;
  }

  public boolean duplicated;
}