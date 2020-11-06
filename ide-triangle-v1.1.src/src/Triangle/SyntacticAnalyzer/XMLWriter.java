/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.SyntacticAnalyzer;

import Triangle.AbstractSyntaxTrees.AnyTypeDenoter;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.BinaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.BoolTypeDenoter;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.ElseCommand;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.FuncProc_Func;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.LoopCommand;
import Triangle.AbstractSyntaxTrees.LoopDoUntilCommand;
import Triangle.AbstractSyntaxTrees.LoopDoWhileCommand;
import Triangle.AbstractSyntaxTrees.LoopForCommand;
import Triangle.AbstractSyntaxTrees.LoopUntilCommand;
import Triangle.AbstractSyntaxTrees.LoopWhileCommand;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.ProcProc_Func;
import Triangle.AbstractSyntaxTrees.Proc_Funcs;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.RecursiveDeclaration;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.VarInitializationDeclaration;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian
 */
public class XMLWriter implements Visitor{
    private FileWriter fileWriter;
    private String fileName;
    public XMLWriter(String filename){
        this.fileName = filename.replace(".tri", ".xml");
        try {
            fileWriter = new FileWriter(this.fileName);
            
        } catch (IOException ex) {
            Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void writeFile(Program ast){
        writeLine("<?xml version=\"1.0\" standalone=\"yes\"?>\n");
        ast.visit(this, null);
        try {
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Object visitAssignCommand(AssignCommand ast, Object o) {
        writeLine("<AssignCommand>");
        ast.V.visit(this, null);
        ast.E.visit(this, null);
        writeLine("</AssignCommand>");
        return null;
    }

    public Object visitCallCommand(CallCommand ast, Object o) {
        writeLine("<CallCommand>");
        ast.I.visit(this, null);
        ast.APS.visit(this, null);
        writeLine("</CallCommand>");
        return null;
    }

    public Object visitEmptyCommand(EmptyCommand ast, Object o) {
        writeLine("<EmptyCommand/>");
        return null;
    }

    public Object visitIfCommand(IfCommand ast, Object o) {
        writeLine("<IfCommand>");
        ast.E.visit(this, null);
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);
        writeLine("</IfCommand>");
        return null;
    }

    @Override
    public Object visitElseCommand(ElseCommand ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object visitLetCommand(LetCommand ast, Object o) {
        writeLine("<LetCommand>");
        ast.D.visit(this, null);
        ast.C.visit(this, null);
        writeLine("</LetCommand>");
        return null;
    }

    public Object visitSequentialCommand(SequentialCommand ast, Object o) {
        writeLine("<SequentialCommand>");
        ast.C1.visit(this, null);
        ast.C2.visit(this, null);
        writeLine("</SequentialCommand>");
        return null;
    }

    @Override
    public Object visitWhileCommand(WhileCommand ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitLoopCommand(LoopCommand ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object visitLoopWhileCommand(LoopWhileCommand ast, Object o) {
        writeLine("<LoopWhileCommand>");
        ast.E.visit(this, null);
        ast.C.visit(this, null);
        writeLine("</LoopWhileCommand>");
        return null;
    }

    public Object visitLoopDoUntilCommand(LoopDoUntilCommand ast, Object o) {
        writeLine("<LoopDoUntilCommand>");
        ast.C.visit(this, null);
        ast.E.visit(this, null);
        writeLine("</LoopDoUntilCommand>");
        return null;
    }

    public Object visitLoopUntilCommand(LoopUntilCommand ast, Object o) {
        writeLine("<LoopUntilCommand>");
        ast.E.visit(this, null);
        ast.C.visit(this, null);
        writeLine("</LoopUntilCommand>");
        return null;
    }

    public Object visitLoopDoWhileCommand(LoopDoWhileCommand aThis, Object o) {
        writeLine("<LoopDoWhileCommand>");
        aThis.C.visit(this, null);
        aThis.E.visit(this, null);
        writeLine("</LoopDoWhileCommand>");
        return null;
    }

    public Object visitLoopForCommand(LoopForCommand aThis, Object o) {
        writeLine("<LoopForCommand>");
        aThis.I.visit(this, null);
        aThis.E1.visit(this, null);
        aThis.E2.visit(this, null);
        aThis.C.visit(this, this);
        writeLine("</LoopForCommand>");
        return null;
    }

    public Object visitArrayExpression(ArrayExpression ast, Object o) {
        writeLine("<ArrayExpression>");
        ast.AA.visit(this, null);
        writeLine("</ArrayExpression>");
        return null;
    }

    public Object visitBinaryExpression(BinaryExpression ast, Object o) {
        writeLine("<BinaryExpression>");
        ast.E1.visit(this, null);
        ast.O.visit(this, null);
        ast.E2.visit(this, null);
        writeLine("</BinaryExpression>");
        return null;
    }

    public Object visitCallExpression(CallExpression ast, Object o) {
        writeLine("<CallExpression>");
        ast.I.visit(this, null);
        ast.APS.visit(this, null);
        writeLine("</CallExpression>");
        return null;
    }

    public Object visitCharacterExpression(CharacterExpression ast, Object o) {
        writeLine("<CharacterExpression>");
        ast.CL.visit(this, null);
        writeLine("</CharacterExpression>");
        return null;
    }

    public Object visitEmptyExpression(EmptyExpression ast, Object o) {
        writeLine("<EmptyExpression>");
        return null;
    }

    public Object visitIfExpression(IfExpression ast, Object o) {
        writeLine("<IfExpression>");
        ast.E1.visit(this, null);
        ast.E2.visit(this, null);
        ast.E3.visit(this, null);
        writeLine("</IfExpression>");
        return null;
    }

    public Object visitIntegerExpression(IntegerExpression ast, Object o) {
        writeLine("<IntegerExpression>");
        ast.IL.visit(this, null);
        writeLine("</IntegerExpression>");
        return null;
    }


    public Object visitLetExpression(LetExpression ast, Object o) {
        writeLine("<LetExpression>");
        ast.D.visit(this, null);
        ast.E.visit(this, null);
        writeLine("</LetExpression>");
        return null;
    }

    public Object visitRecordExpression(RecordExpression ast, Object o) {
        writeLine("<RecordExpression>");
        ast.RA.visit(this, null);
        writeLine("</RecordExpression>");
        return null;
    }

    public Object visitUnaryExpression(UnaryExpression ast, Object obj) {
        writeLine("<UnaryExpression>");
        ast.O.visit(this, null);
        ast.E.visit(this, null);
        writeLine("</UnaryExpression>");
        return null;
    }

    public Object visitVnameExpression(VnameExpression ast, Object obj) {
        writeLine("<VnameExpression>");
        ast.V.visit(this, null);
        writeLine("</VnameExpression>");
        return null;
    }

    // Declarations
    public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object obj) {
        writeLine("<BinaryOperatorDeclaration>");
        ast.O.visit(this, null);
        ast.ARG1.visit(this, null);
        ast.ARG2.visit(this, null);
        ast.RES.visit(this, null);
        writeLine("</BinaryOperatorDeclaration>");
        return null;
    }

    public Object visitConstDeclaration(ConstDeclaration ast, Object obj) {
        writeLine("<ConstDeclaration>");
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        writeLine("</ConstDeclaration>");
        return null;
    }

    public Object visitFuncDeclaration(FuncDeclaration ast, Object obj) {
        writeLine("<FuncDeclaration>");
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        ast.T.visit(this, null);
        ast.E.visit(this, null);
        writeLine("</FuncDeclaration>");
        return null;
    }

    public Object visitProcDeclaration(ProcDeclaration ast, Object obj) {
        writeLine("<ProcDeclaration>");
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        ast.C.visit(this, null);
        writeLine("</ProcDeclaration>");
        return null;
    }

    public Object visitSequentialDeclaration(SequentialDeclaration ast, Object obj) {
        writeLine("<SequentialDeclaration>");
        ast.D1.visit(this, null);
        ast.D2.visit(this, null);
        writeLine("</SequentialDeclaration>");
        return null;
    }

    public Object visitRecursiveDeclaration(RecursiveDeclaration ast, Object o) {
        writeLine("<RecursiveDeclaration>");
        ast.D1.visit(this, null);
        ast.D2.visit(this, null);
        writeLine("</RecursiveDeclaration>");
        return null;
    }
    
    public Object visitTypeDeclaration(TypeDeclaration ast, Object obj) {
        writeLine("<TypeDeclaration>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        writeLine("</TypeDeclaration>");
        return null;
    }

    public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object obj) {
        writeLine("<UnaryOperatorDeclaration>");
        ast.O.visit(this, null);
        ast.ARG.visit(this, null);
        ast.RES.visit(this, null);
        writeLine("</UnaryOperatorDeclaration>");
        return null;
    }

    public Object visitVarDeclaration(VarDeclaration ast, Object obj) {
        writeLine("<VarDeclaration>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        writeLine("</VarDeclaration>");
        return null;
    }
    
    public Object visitVarInitializationDeclaration(VarInitializationDeclaration aThis, Object o) {
        writeLine("<VarExpressionDeclaration>");
        aThis.I.visit(this, null);
        aThis.E.visit(this, null);
        writeLine("</VarExpressionDeclaration>");
        return null;
    }
    
    @Override
    public Object visitProcProc_Func(ProcProc_Func ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        @Override
    public Object visitProc_Funcs(Proc_Funcs ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object visitFuncProc_Func(FuncProc_Func ast, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object obj) {
        writeLine("<MultipleArrayAggregate>");
        ast.E.visit(this, null);
        ast.AA.visit(this, null);
        writeLine("</MultipleArrayAggregate>");
        return null;
    }

    public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object obj) {
        writeLine("<SingleArrayAggregate>");
        ast.E.visit(this, null);
        writeLine("</SingleArrayAggregate>");
        return null;
    }

    public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object obj) {
        writeLine("<MultipleRecordAggregate>");
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        ast.RA.visit(this, null);
        writeLine("</MultipleRecordAggregate>");
        return null;
    }

    public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object obj) {
        writeLine("<SingleRecordAggregate>");
        ast.I.visit(this, null);
        ast.E.visit(this, null);
        writeLine("</SingleRecordAggregate>");
        return null;
    }

    public Object visitConstFormalParameter(ConstFormalParameter ast, Object obj) {
        writeLine("<ConstFormalParameter>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        writeLine("</ConstFormalParameter>");
        return null;
    }

    public Object visitFuncFormalParameter(FuncFormalParameter ast, Object obj) {
        writeLine("<FuncFormalParameter>");
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        ast.T.visit(this, null);
        writeLine("<FuncFormalParameter>");
        return null;
    }

    public Object visitProcFormalParameter(ProcFormalParameter ast, Object obj) {
        writeLine("<ProcFormalParameter>");
        ast.I.visit(this, null);
        ast.FPS.visit(this, null);
        writeLine("</ProcFormalParameter>");
        return null;
    }

    public Object visitVarFormalParameter(VarFormalParameter ast, Object obj) {
        writeLine("<VarFormalParameter>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        writeLine("</VarFormalParameter>");
        return null;
    }

    public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object obj) {
        writeLine("<EmptyFormalParameterSequence/>");
        return null;
    }

    public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object obj) {
        writeLine("<MultipleFormalParameterSequence>");
        ast.FP.visit(this, null);
        ast.FPS.visit(this, null);
        writeLine("</MultipleFormalParameterSequence>");
        return null;
    }

    public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object obj) {
        writeLine("<SingleFormalParameterSequence>");
        ast.FP.visit(this, null);
        writeLine("</SingleFormalParameterSequence>");
        return null;
    }


    public Object visitConstActualParameter(ConstActualParameter ast, Object obj) {
        writeLine("<ConstActualParameter>");
        ast.E.visit(this, null);
        writeLine("</ConstActualParameter>");
        return null;
    }

    public Object visitFuncActualParameter(FuncActualParameter ast, Object obj) {
        writeLine("<FuncActualParameter>");
        ast.I.visit(this, null);
        writeLine("</FuncActualParameter>");
        return null;
    }

    public Object visitProcActualParameter(ProcActualParameter ast, Object obj) {
        writeLine("<ProcActualParameter>");
        ast.I.visit(this, null);
        writeLine("</ProcActualParameter>");
        return null;
    }

    public Object visitVarActualParameter(VarActualParameter ast, Object obj) {
        writeLine("<VarActualParameter>");
        ast.V.visit(this, null);
        writeLine("</VarActualParameter>");
        return null;
    }

    public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object obj) {
        writeLine("<EmptyActualParameterSequence/>");
        return null;
    }

    public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object obj) {
        writeLine("<MultipleActualParameterSequence>");
        ast.AP.visit(this, null);
        ast.APS.visit(this, null);
        writeLine("</MultipleActualParameterSequence>");
        return null;
    }

    public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object obj) {
        writeLine("<SingleActualParameterSequence>");
        ast.AP.visit(this, null);
        writeLine("</SingleActualParameterSequence>");
        return null;
    }

    // Type Denoters
    public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object obj) {
        writeLine("<AnyTypeDenoter/>");
        return null;
    }

    public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object obj) {
        writeLine("<ArrayTypeDenoter>");
        ast.IL.visit(this, null);
        ast.T.visit(this, null);
        writeLine("</ArrayTypeDenoter>");
        return null;
    }

    public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object obj) {
        writeLine("<BoolTypeDenoter/>");
        return null;
    }

    public Object visitCharTypeDenoter(CharTypeDenoter ast, Object obj) {
        writeLine("<CharTypeDenoter/>");
        return null;
    }

    public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object obj) {
        writeLine("<ErrorTypeDenoter/>");
        return null;
    }

    public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object obj) {
        writeLine("<SimpleTypeDenoter>");
        ast.I.visit(this, null);
        writeLine("</SimpleTypeDenoter>");
        return null;
    }

    public Object visitIntTypeDenoter(IntTypeDenoter ast, Object obj) {
        writeLine("<IntTypeDenoter/>");
        return null;
    }

    public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object obj) {
        writeLine("<RecordTypeDenoter>");
        ast.FT.visit(this, null);
        writeLine("</RecordTypeDenoter>");
        return null;
    }

    public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object obj) {
        writeLine("<MultipleFieldTypeDenoter>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        ast.FT.visit(this, null);
        writeLine("</MultipleFieldTypeDenoter>");
        return null;
    }

    public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object obj) {
        writeLine("<SingleFieldTypeDenoter>");
        ast.I.visit(this, null);
        ast.T.visit(this, null);
        writeLine("</SingleFieldTypeDenoter>");
        return null;
    }

       public Object visitCharacterLiteral(CharacterLiteral ast, Object obj) {
        writeLine("<CharacterLiteral value=\"" + ast.spelling + "\"/>");
        return null;
    }

    public Object visitIdentifier(Identifier ast, Object obj) {
        writeLine("<Identifier value=\"" + ast.spelling + "\"/>");
        return null;
    }

    public Object visitIntegerLiteral(IntegerLiteral ast, Object obj) {
        writeLine("<IntegerLiteral value=\"" + ast.spelling + "\"/>");
        return null;
    }

    public Object visitOperator(Operator ast, Object obj) {
        String s = ast.spelling;
        s.replace("<", "&#60;");
        s.replace(">", "&#62;");
        writeLine("<Operator value=\"" + s + "\"/>");
        return null;
    }

    public Object visitDotVname(DotVname ast, Object obj) {
        writeLine("<DotVname>");
        ast.V.visit(this, null);
        ast.I.visit(this, null);
        writeLine("</DotVname>");
        return null;
    }

    public Object visitSimpleVname(SimpleVname ast, Object obj) {
        writeLine("<SimpleVname>");
        ast.I.visit(this, null);
        writeLine("</SimpleVname>");
        return null;
    }

    public Object visitSubscriptVname(SubscriptVname ast, Object obj) {
        writeLine("<SubscriptVname>");
        ast.V.visit(this, null);
        ast.E.visit(this, null);
        writeLine("</SubscriptVname>");
        return null;
    }

    // Programs
    public Object visitProgram(Program ast, Object obj) {
        writeLine("<Program>");
        ast.C.visit(this, null);
        writeLine("</Program>");
        return null;
    }
    public void writeLine(String line){
        try {
            fileWriter.write(line);
            fileWriter.write("\n");
        } catch (IOException ex) {
            Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    
}
