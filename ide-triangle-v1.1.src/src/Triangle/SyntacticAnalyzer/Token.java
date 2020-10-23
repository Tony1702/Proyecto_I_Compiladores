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
    CASES               = 5,
    CASE                = 6,      
    CASE_RANGE          = 7,
    CASE_LITERALS       = 8,      
    CASE_LITERAL        = 9,
    CONST		= 10,
    CONTINUE            = 11,
    DO			= 12,
    ELSECASE		= 13,
    ELSE		= 14,
    ELSEIF              = 15,
    END			= 16,
    FOR                 = 17,
    FROM                = 18,
    FUNC		= 19,
    IF			= 20,
    IN			= 21,
    LEAVE               = 22,
    LET			= 23,
    LOCAL               = 24,
    LOOP                = 25,
    OF			= 26,
    PROC		= 27,
    RECORD		= 28,
    RECURSIVE           = 29,
    SELECT              = 30,
    SKIP                = 31,
    THEN		= 32,
    TO                  = 33,
    TYPE		= 34,
    UNTIL               = 35,
    VAR			= 36,
    WHILE		= 37,

    // punctuation...
    DOT			= 38,
    COLON		= 39,
    SEMICOLON           = 40,
    COMMA		= 41,
    BECOMES		= 42,
    IS			= 43,
    PIPE                = 44,
    TWODOTS             = 45,

    // brackets...
    LPAREN		= 46,
    RPAREN		= 47,
    LBRACKET            = 48,
    RBRACKET            = 49,
    LCURLY		= 50,
    RCURLY		= 51,

    // special tokens...
    EOT			= 52,
    ERROR		= 53;

  private static String[] tokenTable = new String[] {
    "<int>",
    "<char>",
    "<identifier>",
    "<operator>",
    "array",
    "cases",
    "case",
    "case-range",
    "case-literals",
    "case-literal",
    "const",
    "continue",
    "do",
    "elsecase",
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

}
