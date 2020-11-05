/*
 * @(#)Token.java                        2.1 2003/10/07
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


final class Token extends Object {

  protected int kind;
  protected String spelling;
  protected SourcePosition position;

  public Token(int kind, String spelling, SourcePosition position) {

    if (kind == Token.IDENTIFIER) {
      int currentKind = firstReservedWord;
      boolean searching = true;

      while (searching) {
        int comparison = tokenTable[currentKind].compareTo(spelling);
        if (comparison == 0) {
          this.kind = currentKind;
          searching = false;
        } else if (comparison > 0 || currentKind == lastReservedWord) {
          this.kind = Token.IDENTIFIER;
          searching = false;
        } else {
          currentKind ++;
        }
      }
    } else
      this.kind = kind;

    this.spelling = spelling;
    this.position = position;

  }

  public static String spell (int kind) {
    return tokenTable[kind];
  }

  public String toString() {
    return "Kind=" + kind + ", spelling=" + spelling +
      ", position=" + position;
  }

  // Token classes...

  public static final int

    // literals, identifiers, operators...
    INTLITERAL	= 0,
    CHARLITERAL	= 1,
    IDENTIFIER	= 2,
    OPERATOR	= 3,

    // reserved words - must be in alphabetical order...
    ARRAY		= 4,
//    CASES               = 5,
    CASE                = 5,      
//    CASE_RANGE          = 7,
//    CASE_LITERALS       = 8,      
//    CASE_LITERAL        = 9,
    CONST		= 6,
    CONTINUE            = 7,
    DO			= 8,
//    ELSECASE		= 13,
    ELSE		= 9,
    ELSEIF              = 10,
    END			= 11,
    FOR                 = 12,
    FROM                = 13,
    FUNC		= 14,
    IF			= 15,
    IN			= 16,
    LEAVE               = 17,
    LET			= 18,
    LOCAL               = 19,
    LOOP                = 20,
    OF			= 21,
    PROC		= 22,
    RECORD		= 23,
    RECURSIVE           = 24,
    SELECT              = 25,
    SKIP                = 26,
    THEN		= 27,
    TO                  = 28,
    TYPE		= 29,
    UNTIL               = 30,
    VAR			= 31,
    WHILE		= 32,

    // punctuation...
    DOT			= 33,
    COLON		= 34,
    SEMICOLON           = 35,
    COMMA		= 36,
    BECOMES		= 37,
    IS			= 38,
    PIPE                = 39,
    TWODOTS             = 40,

    // brackets...
    LPAREN		= 41,
    RPAREN		= 42,
    LBRACKET            = 43,
    RBRACKET            = 44,
    LCURLY		= 45,
    RCURLY		= 46,

    // special tokens...
    EOT			= 50,
    ERROR		= 51;

  private static String[] tokenTable = new String[] {
    "<int>",
    "<char>",
    "<identifier>",
    "<operator>",
    "array",
//    "cases",
    "case",
//    "case-range",
//    "case-literals",
//    "case-literal",
    "const",
    "continue",
    "do",
//    "elsecase",
    "else",
    "elseif",
    "end",
    "for",
    "from",
    "func",
    "if",
    "in",
    "leave",
    "let",
    "local",
    "loop",
    "of",
    "proc",
    "record",
    "recursive",
    "select",
    "skip",
    "then",
    "to",
    "type",
    "until",
    "var",
    "while",
    ".",
    ":",
    ";",
    ",",
    ":=",
    "~",
    "|",
    "..",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "",
    "<error>"
  };

  private final static int	firstReservedWord = Token.ARRAY,
  				lastReservedWord  = Token.WHILE;
    public static boolean isReservedWord(String token){
        for(int i=4; i<32; i++){
            if(tokenTable[i] == token){
                return true;
            }
        }
        return false;
    }
}
