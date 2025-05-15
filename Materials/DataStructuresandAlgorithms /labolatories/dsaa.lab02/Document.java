package dsaa.lab02;

import java.util.Iterator;
import java.util.Scanner;

public class Document{
    public String name;
    public OneWayLinkedList<Link> links;
    public Document(String name, Scanner scan) {
        this.name = name;
        this.links = new OneWayLinkedList<>();
        load(scan);
    }
    public void load(Scanner scan) {
        String link;
        link=scan.next();
        while (!(link.equals("eod")))
        {
            if(correctLink(link))
                links.add(new Link(link.substring(5).toLowerCase()));
            link=scan.next();
        }
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String link) {
        if(link.length()<6)
            return false;
        if((link.toLowerCase().startsWith("link="))&&(Character.isLetter(link.charAt(5))))
            return link.substring(5).matches("[0-9a-zA-Z_]+");
        else return false;
    }

    @Override
    public String toString() {
        String answer = "Document: "+this.name;
        Iterator<Link> iter =links.iterator();
        while(iter.hasNext())
            answer+=("\n"+iter.next().ref);
        return answer;
    }

}
