import java.util.LinkedList;

public class Family {
    private Cederman familyHead;
    private LinkedList<Cederman> family;
    private String familyName;
    private int influence;

    public Family(Cederman familyHead){
        family = new LinkedList<Cederman>();
        this.familyHead = familyHead;
    }

    public void addFamilyMember(Cederman baby){
        family.add(baby);
    }

    public Cederman getFamilyHead(){
        return familyHead;
    }
}
