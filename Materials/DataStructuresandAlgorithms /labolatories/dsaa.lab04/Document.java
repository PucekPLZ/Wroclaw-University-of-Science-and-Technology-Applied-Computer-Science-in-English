package dsaa.lab04;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }
    public void load(Scanner scan) {
        String slink;
        slink=scan.next();
        while (!(slink.equals("eod")))
        {
            if(correctLink(slink))
                link.add(createLink(slink.substring(5).toLowerCase()));
            slink = scan.next();
        }
    }

    public static boolean correctLink(String link)
    {
        if(link.length()<6)
            return false;
        if((link.toLowerCase().startsWith("link="))&&(Character.isLetter(link.charAt(5))))
            return link.substring(5).matches("[0-9a-zA-Z_]+\\(([1-9]\\d+|[1-9])\\)|[0-9a-zA-Z_]+");
        else return false;
    }
    public static boolean isCorrectId(String id) {
        return (Character.isLetter(id.charAt(0)) && id.matches("[0-9a-zA-Z_]+"));
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    public static Link createLink(String link) {
        if (link.contains("("))
            return new Link(link.substring(0,link.indexOf('(')),Integer.parseInt(link.substring(link.indexOf("(")+1, link.indexOf(")"))));
        else return new Link(link);
    }

    @Override
    public String toString() {
        String retStr="Document: "+name;
            Iterator<Link> iter = link.iterator();
            while (iter.hasNext())
            {
                retStr += "\n";
                for (int i = 0; i < 10; i++) {
                    if (i != 0 && iter.hasNext())
                        retStr += " ";
                    if (iter.hasNext())
                        retStr += iter.next().toString();
                    else return retStr;
                }
            }
        return retStr;
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        if(link.size != 0)
        {
            ListIterator<Link> iter=link.listIterator();
            while (iter.hasPrevious()) {
                retStr += "\n";
                for (int i = 0; i < 10; i++) {
                    if (i != 0 && iter.hasPrevious())
                        retStr += " ";
                    if (iter.hasPrevious())
                        retStr += iter.previous().toString();
                    else return retStr;
                }
            }
        }
        return retStr;
    }
}
