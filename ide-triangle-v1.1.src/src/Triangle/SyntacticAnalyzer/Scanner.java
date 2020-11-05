/*
 * @(#)Scanner.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */
package Triangle.SyntacticAnalyzer;

public final class Scanner {

    private SourceFile sourceFile;
    private boolean debug;

    private char currentChar;
    private StringBuffer currentSpelling;
    private boolean currentlyScanningToken;

    private String htmlContent;
    private String htmlComment;
    private String htmlSpaces;
    private int counter;

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

// isOperator returns true iff the given character is an operator character.
    private boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/'
                || c == '=' || c == '<' || c == '>' || c == '\\'
                || c == '&' || c == '@' || c == '%' || c == '^'
                || c == '?');
    }

///////////////////////////////////////////////////////////////////////////////
    public Scanner(SourceFile source) {
        sourceFile = source;
        currentChar = sourceFile.getSource();
        debug = false;
        htmlContent = "";
        htmlComment = "";
        htmlSpaces = "";
        counter = 0;
    }

    public void enableDebugging() {
        debug = true;
    }

    // takeIt appends the current character to the current token, and gets
    // the next character from the source program.
    private void takeIt() {
        if (currentlyScanningToken) {
            currentSpelling.append(currentChar);
        }
        currentChar = sourceFile.getSource();
    }

    // scanSeparator skips a single separator.
    private void scanSeparator() {
        switch (currentChar) {
            case '!': {
                addToHtmlComment(String.valueOf(currentChar));
                takeIt();
                while ((currentChar != SourceFile.EOL) && (currentChar != SourceFile.EOT)) {
                    addToHtmlComment(String.valueOf(currentChar));
                    takeIt();
                }
                addHtmlComment();
                if (currentChar == SourceFile.EOL) {
                    addHtmlJumpLine();
                    takeIt();
                }
            }
            break;
            case SourceFile.EOT:
                takeIt();
                addHtmlSpace();
                break;
            case ' ': {
                takeIt();
                htmlSpaces += "&nbsp";
                break;
            }
            case '\n': {
                takeIt();
                addHtmlJumpLine();
                break;
            }
            case '\r': {
                takeIt();
                addHtmlSpace();
                break;
            }
            case '\t':
                takeIt();
                addHtmlTab();
                break;
        }
    }

    private int scanToken() {

        switch (currentChar) {

            case 'a': case 'b': case 'c': case 'd': case 'e': 
            case 'f': case 'g': case 'h': case 'i': case 'j': 
            case 'k': case 'l': case 'm': case 'n': case 'o': 
            case 'p': case 'q': case 'r': case 's': case 't':
            case 'u': case 'v': case 'w': case 'x': case 'y':
            case 'z':
            case 'A': case 'B': case 'C': case 'D': case 'E':
            case 'F': case 'G': case 'H': case 'I': case 'J':
            case 'K': case 'L': case 'M': case 'N': case 'O':
            case 'P': case 'Q': case 'R': case 'S': case 'T':
            case 'U': case 'V': case 'W': case 'X': case 'Y':
            case 'Z':
                takeIt();
                while (isLetter(currentChar) || isDigit(currentChar)) {
                    takeIt();
                }
                addHtmlIdentifier();
                return Token.IDENTIFIER;

            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                takeIt();
                while (isDigit(currentChar)) {
                    takeIt();
                }
                addHtmlLiteral();
                return Token.INTLITERAL;

            case '+': case '-': case '*': case '/': case '=':
            case '<': case '>': case '\\': case '&': case '@':
            case '%': case '^': case '?':
                takeIt();
                while (isOperator(currentChar)) {
                    takeIt();
                }
                addHtmlIdentifier();
                return Token.OPERATOR;

            case '\'':
                takeIt();
                takeIt(); // the quoted character
                if (currentChar == '\'') {
                    takeIt();
                    addHtmlLiteral();
                    return Token.CHARLITERAL;
                } else {
                    return Token.ERROR;
                }

            case '.':
                takeIt();
                addHtmlLiteral();
                return Token.DOT;

            case ':':
                takeIt();
                if (currentChar == '=') {
                    takeIt();
                    addHtmlLiteral();
                    return Token.BECOMES;
                } else {
                    addHtmlLiteral();
                    return Token.COLON;
                }

            case ';':
                takeIt();
                addHtmlLiteral();
                return Token.SEMICOLON;

            case ',':
                takeIt();
                addHtmlLiteral();
                return Token.COMMA;

            case '~':
                takeIt();
                addHtmlLiteral();
                return Token.IS;

            case '(':
                takeIt();
                addHtmlLiteral();
                return Token.LPAREN;

            case ')':
                takeIt();
                addHtmlLiteral();
                return Token.RPAREN;

            case '[':
                takeIt();
                addHtmlLiteral();
                return Token.LBRACKET;

            case ']':
                takeIt();
                addHtmlLiteral();
                return Token.RBRACKET;

            case '{':
                takeIt();
                addHtmlLiteral();
                return Token.LCURLY;

            case '}':
                takeIt();
                addHtmlLiteral();
                return Token.RCURLY;

            case SourceFile.EOT:
                return Token.EOT;

            default:
                takeIt();
                return Token.ERROR;
        }
    }

    public Token scan() {
        Token tok;
        SourcePosition pos;
        int kind;
        currentSpelling = new StringBuffer("");
        currentlyScanningToken = false;
        while (currentChar == '!'
                || currentChar == ' '
                || currentChar == '\n'
                || currentChar == '\r'
                || currentChar == '\t') {
            scanSeparator();
        }

        currentlyScanningToken = true;
        currentSpelling = new StringBuffer("");
        pos = new SourcePosition();
        pos.start = sourceFile.getCurrentLine();

        kind = scanToken();

        pos.finish = sourceFile.getCurrentLine();
        tok = new Token(kind, currentSpelling.toString(), pos);
        if (debug) {
            System.out.println(tok);
        }
        return tok;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    private void addHtmlReservedWord() {
        htmlContent += "<FONT FACE= \"monospace\" SIZE =3 COLOR=#000000><strong>" + htmlSpaces + currentSpelling.toString() + "</strong></FONT>";
        htmlSpaces = "";
        counter++;

    }

    private void addHtmlIdentifier() {
        String token = currentSpelling.toString();
        if (Token.isReservedWord(token)) {
            addHtmlReservedWord();
        } else {
            htmlContent += "<FONT FACE= \"monospace\" SIZE =3 COLOR=#000000>" + htmlSpaces + token + "</FONT>";
            htmlSpaces = "";
            counter++;
        }
    }

    //agrega texto con formato para las literales
    private void addHtmlLiteral() {
        htmlContent += "<FONT FACE= \"monospace\" SIZE =3 COLOR=#000099>" + htmlSpaces + currentSpelling.toString() + "</FONT>";
        htmlSpaces = "";
        counter++;
    }

    private void addToHtmlComment(String newChar) {
        htmlComment += newChar;
    }
    //agrega texto con formato para las comentarios

    private void addHtmlComment() {
        htmlContent += "<FONT FACE= \"monospace\" SIZE =3 COLOR=#008000>" + htmlSpaces + htmlComment + "</FONT>";
        htmlComment = "";
        htmlSpaces = "";
        counter++;
    }

    //agrega los espacios que se van acumulando cuando se lee
    private void addHtmlSpace() {
        htmlContent += "<FONT FACE= \"monospace\" SIZE =3 COLOR=#000000>" + htmlSpaces + currentSpelling.toString() + "</FONT>";
        htmlSpaces = "";
        counter++;
    }

    //agrega el salto de linea al html
    private void addHtmlJumpLine() {
        htmlContent += "<br/>";
        htmlSpaces = "";
        counter++;
    }

    private void addHtmlTab() {
        htmlContent += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    }

}
