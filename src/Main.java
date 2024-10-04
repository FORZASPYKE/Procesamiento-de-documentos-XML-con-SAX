import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {

            File archivo = new File("C:\\Users\\GRIMM\\IdeaProjects\\XMLconSAX\\src\\sales.xml");


            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.parse(archivo);

            doc.getDocumentElement().normalize();

            double ventasHealth = 0;
            double ventasComputers = 0;
            double ventasSports = 0;
            double ventasMusic = 0;
            double ventasMovies = 0;
            double ventasGarden = 0;

            NodeList listaVentas = doc.getElementsByTagName("sale_record");

            for (int i = 0; i < listaVentas.getLength(); i++) {
                Element venta = (Element) listaVentas.item(i);

                String departamento = venta.getElementsByTagName("department").item(0).getTextContent();
                String ventasTexto = venta.getElementsByTagName("sales").item(0).getTextContent();
                double ventas = Double.parseDouble(ventasTexto);

                if (departamento.equals("Health")) {
                    ventasHealth += ventas;
                } else if (departamento.equals("Computers")) {
                    ventasComputers += ventas;
                } else if (departamento.equals("Sports")) {
                    ventasSports += ventas;
                } else if (departamento.equals("Music")) {
                    ventasMusic += ventas;
                } else if (departamento.equals("Movies")) {
                    ventasMovies += ventas;
                } else if (departamento.equals("Garden")) {
                    ventasGarden += ventas;
                }
            }

            System.out.println("Ventas por departamento:");
            System.out.println("Health: $" + ventasHealth);
            System.out.println("Computers: $" + ventasComputers);
            System.out.println("Sports: $" + ventasSports);
            System.out.println("Music: $" + ventasMusic);
            System.out.println("Movies: $" + ventasMovies);
            System.out.println("Garden: $" + ventasGarden);

            double ventasTotales = ventasHealth + ventasComputers + ventasSports + ventasMusic + ventasMovies + ventasGarden;
            System.out.println("Ventas Totales: $" + ventasTotales);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
