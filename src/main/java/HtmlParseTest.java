import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;

public class HtmlParseTest {


  private HtmlParseTest() {
  }

  public static void main(String[] args) {
    try {

      Parser parser = Parser.createParser(
          "TWIN CLASSIC OCEAN VIEW NON-REFUNDABLE Note: Rates are valid only as part of a package.<br />One or more travel related components (Air tickets, car rental and train tickets only) must be combined with the hotel rates as a package. Stand alone rates will at no time be visible to the final customer.<br /><br /><br /><br />Tax Recovery Charges and Service Fees: Included<br /><br />Requested Room Bedding - Not Guaranteed: Twin Classic Ocean View - 2 twin beds(Twin); Twin Classic Ocean View - 2 twin beds(Twin)<br /><br />STARTING 09/05/2018 CXL-PENALTY FEE IS 100.00% OF BOOKING PRICE.<br /><br /><b>Know Before You Go</b></p> <br /><ul>  <li>Only registered guests are allowed in the guestrooms. </li> <li>The property has connecting/adjoining rooms, which are subject to availability and can be requested by contacting the property using the number on the booking confirmation. </li><li>No pets and no service animals are allowed at this property. </li> </ul><b>Fees</b> <br />The following fees and deposits are charged by the property at time of service, check-in, or check-out.  <ul> <li>Fee for buffet breakfast: THB 700 for adults and THB 350 for children (approximately) </li> <li>Fee for in-room wireless Internet: THB 300 per day (rates may vary)</li>  <li>Fee for wireless Internet in public areas: THB 300 per day (rates may vary)</li>                <li>Rollaway bed fee: THB 2531.0 per night</li>            </ul> The above list may not be comprehensive. Fees and deposits may not include tax and are subject to change. <br /><br /> Deposit Required.<br /> Deposit Required. Please note that even if the total price has been computed accurately, the price breakdown might be just indicative.",
          "utf-8");
      String result = "";
      for (NodeIterator i = parser.elements(); i.hasMoreNodes(); ) {
        Node node = i.nextNode();
        result+=message(node.toHtml());
      }
      System.out.println(result);
    } catch (Exception ex) {
      ex.getMessage();
    }
  }

  private static String ENCODE = "UTF-8";

  private static String message(String szMsg) {

    try {
      return new String(szMsg.getBytes(ENCODE), System.getProperty("file.encoding"));
    } catch (Exception e) {
    }
    return "";
  }
}
