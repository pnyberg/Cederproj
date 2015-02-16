import java.util.LinkedList;

public class Clan{
    private LinkedList<Family> clan;
    private Cederman clanLeader;
    private String clanName;

    public Clan(Family family1,Family family2){
        clan = new LinkedList<Family>();
        clanLeader = choseLeader(family1, family2);
    }
    private Cederman choseLeader(Family family1, Family family2){
        if (family1.getFamilyHead().getInfluence() > family2.getFamilyHead().getInfluence()) {
            return family1.getFamilyHead();
        }else{
            return family2.getFamilyHead();
        }
    }


}
