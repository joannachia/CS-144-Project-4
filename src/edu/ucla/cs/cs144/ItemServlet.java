package edu.ucla.cs.cs144;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    /* Non-recursive (NR) version of Node.getElementsByTagName(...)
     */
    static Element[] getElementsByTagNameNR(Element e, String tagName) {
        Vector< Element > elements = new Vector< Element >();
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
            {
                elements.add( (Element)child );
            }
            child = child.getNextSibling();
        }
        Element[] result = new Element[elements.size()];
        elements.copyInto(result);
        return result;
    }

    /* Returns the first subelement of e matching the given tagName, or
     * null if one does not exist. NR means Non-Recursive.
     */
    static Element getElementByTagNameNR(Element e, String tagName) {
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
                return (Element) child;
            child = child.getNextSibling();
        }
        return null;
    }

    /* Returns the text associated with the given element (which must have
     * type #PCDATA) as child, or "" if it contains no text.
     */
    static String getElementText(Element e) {
        if (e.getChildNodes().getLength() == 1) {
            Text elementText = (Text) e.getFirstChild();
            return elementText.getNodeValue();
        }
        else
            return "";
    }

    /* Returns the text (#PCDATA) associated with the first subelement X
     * of e with the given tagName. If no such X exists or X contains no
     * text, "" is returned. NR means Non-Recursive.
     */
    static String getElementTextByTagNameNR(Element e, String tagName) {
        Element elem = getElementByTagNameNR(e, tagName);
        if (elem != null)
            return getElementText(elem);
        else
            return null;
    }

    static String getAttributeValue(Element e, String attributeName) {
        String attributeValue = e.getAttribute(attributeName);
        if (attributeValue.isEmpty())
            return null;
        else
            return attributeValue;
    }

    /* Returns the amount (in XXXXX.xx format) denoted by a money-string
     * like $3,453.23. Returns the input if the input is an empty string.
     */
    static String strip(String money) {
        if (money.equals("") || money.equals("NULL"))
            return "NULL";
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try { am = nf.parse(money).doubleValue(); }
            catch (ParseException e) {
                System.out.println("This method should work for all " +
                        "money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }

    static Date createDate(String timeStamp) {
        SimpleDateFormat oldFormat = new SimpleDateFormat("MMM-dd-yy HH:mm:ss");
        Date date = null;
        try {
            date = oldFormat.parse(timeStamp);
        } catch (ParseException pe) {
            pe.printStackTrace();
            System.exit(3);
        }
        return date;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String itemXML = AuctionSearchClient.getXMLDataForItemId(request.getParameter("id"));
        Item item = new Item();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        PrintWriter writer = response.getWriter();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(itemXML));
            Document document = documentBuilder.parse(is);

            Element itemElement = document.getDocumentElement();

            item.setId(Integer.parseInt(getAttributeValue(itemElement, "ItemID")));
            item.setName(getElementTextByTagNameNR(itemElement, "Name"));

            Element[] categoryElements = getElementsByTagNameNR(itemElement, "Category");
            ArrayList<String> categories = new ArrayList<String>();
            for (Element categoryElement : categoryElements) {
                categories.add(getElementText(categoryElement));
            }
            item.setCategories(categories);

            item.setCurrently(Double.parseDouble(strip(getElementTextByTagNameNR(itemElement, "Currently"))));
            if (getElementTextByTagNameNR(itemElement, "Buy_Price") != null) {
                item.setBuyPrice(Double.parseDouble(strip(getElementTextByTagNameNR(itemElement, "Buy_Price"))));
            }
            item.setFirstBid(Double.parseDouble(strip(getElementTextByTagNameNR(itemElement, "First_Bid"))));
            item.setNumberOfBids(Integer.parseInt(getElementTextByTagNameNR(itemElement, "Number_of_Bids")));

            Element bidsElement = getElementByTagNameNR(itemElement, "Bids");
            Element[] bidElements = getElementsByTagNameNR(bidsElement, "Bids");
            ArrayList<Bid> bids = new ArrayList<Bid>();
            for (Element bidElement : bidElements) {
                Bid bid = new Bid();
                User bidder = new User();
                Element bidderElement = getElementByTagNameNR(bidElement, "Bidder");
                bidder.setId(getAttributeValue(bidderElement, "UserID"));
                bidder.setRating(Integer.parseInt(getAttributeValue(bidderElement, "Rating")));

                Element bidderLocationElement = getElementByTagNameNR(bidderElement, "Location");
                if (bidderLocationElement != null) {
                    Location bidderLocation = new Location();
                    bidderLocation.setLatitude(Double.parseDouble(getAttributeValue(bidderLocationElement, "Latitude")));
                    bidderLocation.setLongitude(Double.parseDouble(getAttributeValue(bidderLocationElement, "Longitude")));
                    bidder.setLocation(bidderLocation);
                }

                bidder.setCountry(getElementTextByTagNameNR(bidderElement, "Country"));
                bid.setBidder(bidder);
                bid.setTime(createDate(getElementTextByTagNameNR(bidderElement, "Time")));
                bid.setAmount(Double.parseDouble(strip(getElementTextByTagNameNR(bidderElement, "Amount"))));
                bids.add(bid);
            }
            item.setBids(bids);

            Element locationElement = getElementByTagNameNR(itemElement, "Location");
            Location location = new Location();
            location.setLatitude(Double.parseDouble(getAttributeValue(locationElement, "Latitude")));
            location.setLatitude(Double.parseDouble(getAttributeValue(locationElement, "Longitude")));
            item.setLocation(location);
            item.setCountry(getElementTextByTagNameNR(itemElement, "Country"));
            item.setStarted(createDate(getElementTextByTagNameNR(itemElement, "Started")));
            item.setEnds(createDate(getElementTextByTagNameNR(itemElement, "Ends")));

            Element sellerElement = getElementByTagNameNR(itemElement, "Seller");
            User seller = new User();
            seller.setId(getAttributeValue(sellerElement, "UserID"));
            seller.setRating(Integer.parseInt(getAttributeValue(sellerElement, "Rating")));
            item.setSeller(seller);
            item.setDescription(getElementTextByTagNameNR(itemElement, "Description"));

            request.setAttribute("item", item);

            writer.println(item.toString());
        } catch (ParserConfigurationException pce) {
            writer.println("unable to create a document builder");
        } catch (SAXException sae) {
            writer.println("error parsing xml");
        }
        writer.close();

//        RequestDispatcher requestDispatcher;
//		requestDispatcher = request.getRequestDispatcher("/itemResult.jsp");
//		requestDispatcher.forward(request, response);

    }
}
