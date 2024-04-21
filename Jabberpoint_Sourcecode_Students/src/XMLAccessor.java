import java.io.*;
import java.util.List;
import java.util.Optional;
import javax.swing.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

public class XMLAccessor implements Accessor {
	protected static final String DEFAULT_API_TO_USE = "dom";
	protected static final String SHOWTITLE = "showtitle";
	protected static final String SLIDETITLE = "title";
	protected static final String SLIDE = "slide";
	protected static final String ITEM = "item";
	protected static final String LEVEL = "level";
	protected static final String KIND = "kind";
	protected static final String TEXT = "text";
	protected static final String IMAGE = "image";
	protected static final String PCE = "Parser Configuration Exception";
	protected static final String UNKNOWNTYPE = "Unknown Element type";
	protected static final String NFE = "Number Format Exception";

	private String getTitle(Element element, String tagName) {
		return element.getElementsByTagName(tagName).item(0).getTextContent();
	}

	public String loadNewFile() {
		String data = HelpingClass.getStringInput("Paste the New XML", 400, 200);
		if (data != null) {
			String fileName = HelpingClass.getFileNameInput("Enter a file name without Extension:", "Save As");
			if (fileName != null) {
				saveNewFile(data, fileName + ".xml");
				return fileName + ".xml";
			}
		}
		return null;
	}

	private void saveNewFile(String fileData, String fileName) {
		try (FileOutputStream outputStream = new FileOutputStream(new File(fileName))) {
			outputStream.write(fileData.getBytes());
			System.out.println("New File created successfully.");
		} catch (IOException e) {
			System.err.println("New File not created successfully: " + e.getMessage());
		}
	}

	public void loadFile(Presentation presentation, String filename) throws IOException {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename));
			Element doc = document.getDocumentElement();
			presentation.setTitle(getTitle(doc, SHOWTITLE));

			NodeList slides = doc.getElementsByTagName(SLIDE);
			for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);
				Slide slide = new Slide();
				slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
				presentation.append(slide);

				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
				for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
					loadSlideItem(slide, (Element) slideItems.item(itemNumber));
				}
			}
		} catch (SAXException | ParserConfigurationException e) {
			throw new IOException("Failed to load XML file: " + e.getMessage(), e);
		}
	}

	private void loadSlideItem(Slide slide, Element item) {
		int level = Integer.parseInt(item.getAttribute(LEVEL));
		String type = item.getAttribute(KIND);
		if (type.equals(TEXT)) {
			slide.append(new TextItem(level, item.getTextContent()));
		} else if (type.equals(IMAGE)) {
			slide.append(new BitmapItem(level, item.getTextContent()));
		} else {
			System.err.println(UNKNOWNTYPE);
		}
	}

	public void saveFile(Presentation presentation, String filename) throws IOException {
		try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
			out.println("<?xml version=\"1.0\"?>");
			out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
			out.println("<presentation>");
			out.print("<showtitle>");
			out.print(presentation.getTitle());
			out.println("</showtitle>");
			for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
				Optional<Slide> optionalSlide = Optional.ofNullable(presentation.getSlide(slideNumber));
				optionalSlide.ifPresent(slide -> {
					out.println("<slide>");
					out.println("<title>" + slide.getTitle() + "</title>");
					List<SlideItem> slideItems = slide.getSlideItems();
					for (SlideItem slideItem : slideItems) {
						out.print("<item kind=\"" + (slideItem instanceof TextItem ? "text" : "image"));
						out.print("\" level=\"" + slideItem.getLevel() + "\">");
						out.print(slideItem instanceof TextItem ? ((TextItem) slideItem).getText() : ((BitmapItem) slideItem).getName());
						out.println("</item>");
					}
					out.println("</slide>");
				});
			}
			out.println("</presentation>");
		}
	}

	public String LoadNewFile() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select XML File");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
		fileChooser.setAcceptAllFileFilterUsed(false);

		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getAbsolutePath();
		}
		return null; // Return null or handle differently if no file was selected
	}
}
