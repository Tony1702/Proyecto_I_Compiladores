/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

/**
 *
 * @author Adrian
 */
import Triangle.SyntacticAnalyzer.SourcePosition;

public class LoopCommand extends Command {
        
    public LoopCommand (Identifier iAST, ActualParameterSequence apsAST,
               SourcePosition thePosition) {
        super (thePosition);
        I = iAST;
        APS = apsAST;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitLoopCommand(this, o);
    }

    public Identifier I;
    public ActualParameterSequence APS;
}
