package dsaa.lab03;

import java.util.Iterator;
import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayUnorderedListWithHeadAndTail<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name;
        link=new TwoWayUnorderedListWithHeadAndTail<Link>();
        load(scan);
    }
    public void load(Scanner scan) {
        String slink;
        slink=scan.next();
        while (!(slink.equals("eod")))
        {
            if(correctLink(slink))
                link.add(new Link(slink.substring(5).toLowerCase()));
            slink=scan.next();
        }
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    public static boolean correctLink(String link) {
        if(link.length()<6)
            return false;
        if((link.toLowerCase().startsWith("link="))&&(Character.isLetter(link.charAt(5))))
            return link.substring(5).matches("[0-9a-zA-Z_]+");
        else return false;
    }

    @Override
    public String toString() {
        String answer = "Document: "+this.name;
        Iterator<Link> iter = link.iterator();
        while(iter.hasNext())
            answer+=("\n"+iter.next().ref);
        return answer;
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        return retStr+link.toStringReverse();
    }

}