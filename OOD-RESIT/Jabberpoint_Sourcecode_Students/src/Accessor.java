import java.io.IOException;

public interface Accessor {
	String DEMO_NAME = "Demo presentation";
	String DEFAULT_EXTENSION = ".xml";

	static Accessor getDemoAccessor() {
		return new DemoPresentation();
	}

	void loadFile(Presentation presentation, String filename) throws IOException;
	void saveFile(Presentation presentation, String filename) throws IOException;
}
