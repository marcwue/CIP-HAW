package nodes;

import descriptoren.AbstractDescr;
import descriptoren.ProcDescr;
import descriptoren.SymbolTable;

public class ProcedureBodyNode extends AbstractNode {
    private final DeclarationsNode declarations;
    private final AbstractNode statementseq;
    private final int linkage;
    private IdentNode decIdent;

    public ProcedureBodyNode(IdentNode decIdent, DeclarationsNode declarations, AbstractNode statementseq) {
        this.decIdent = decIdent;
        this.declarations = declarations;
        this.statementseq = statementseq;
        this.linkage = 3;
    }

    public AbstractDescr compile(SymbolTable symbolTable, SymbolTable symbolTableL, int pushBackSize) {

        int label = getNextLabelNumber();

        if (declarations != null) {
            declarations.compile(symbolTableL);
        }
        int allocatedMemory = linkage + symbolTableL.getSize();
        write("LABEL, " + label);

        //start
        write("INIT, " + allocatedMemory);
        write("PUSHREG, RK");
        write("PUSHREG, FP");
        //rette sich wer kann
        write("PUSHI, " + symbolTableL.getLevel());
        write("PUSHREG, SL");
        // fp auf stack pointer addresse setzen
        write("GETSP");
        write("SETFP");
        // sl auf frame pointer setzen
        write("GETFP");
        write("PUSHI, " + symbolTableL.getLevel());
        write("SETSL");
        // SP := SP + lokale variablen
        write("GETSP");
        write("PUSHI, " + symbolTableL.getSize());
        write("ADD");
        write("SETSP");
        // end

        if (statementseq != null) {
            statementseq.compile(symbolTableL);
        } else {
            System.out.println("no StatementSequenzNode");
        }

        // alles wieder zurück bauen
        write("GETFP");
        write("SETSP");
        // sl zurücksetzen
        write("PUSHI, " + symbolTableL.getLevel());
        write("POPREG, SL");
        write("POPREG, FP");
        write("POPREG, RK");
        // stackpointer zurückverschieben
        write("GETSP");
        write("PUSHI, " + pushBackSize);
        write("SUB");
        write("SETSP");

        int reduceVal = allocatedMemory + pushBackSize;
        write("REDUCE, " + reduceVal);
        write("RET");

        ProcDescr descr = new ProcDescr(label, symbolTableL);
        symbolTable.declare(decIdent.getIdentName(), descr);
        return descr;
    }

    @Override
    public String toString() {
        return "ProcedureBodyNode{" + "declarations=" + declarations
                + ", statseq=" + statementseq + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ProcedureBodyNode that = (ProcedureBodyNode) o;

        if (declarations != null ? !declarations.equals(that.declarations)
                : that.declarations != null)
            return false;
        if (statementseq != null ? !statementseq.equals(that.statementseq)
                : that.statementseq != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = declarations != null ? declarations.hashCode() : 0;
        result = 31 * result + (statementseq != null ? statementseq.hashCode() : 0);
        return result;
    }
}
