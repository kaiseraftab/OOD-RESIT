import java.util.ArrayList;
import java.util.Optional;

public class Presentation {
	private static final int NO_SLIDE_INDEX = -1;
	private String showTitle;
	private ArrayList<Slide> showList = new ArrayList<>();
	private int currentSlideNumber = NO_SLIDE_INDEX;
	private SlideViewerComponent slideViewComponent;

	public Presentation() {
	}

	public Presentation(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	public int getSize() {
		return showList.size();
	}

	public String getTitle() {
		return showTitle;
	}

	public void setTitle(String nt) {
		showTitle = nt;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	public int getSlideNumber() {
		return currentSlideNumber;
	}

	public void setSlideNumber(int number) {
		if (number < NO_SLIDE_INDEX || number >= getSize()) {
			throw new IllegalArgumentException("Invalid slide number: " + number);
		}
		currentSlideNumber = number;
		updateViewComponent();
	}

	private void updateViewComponent() {
		if (slideViewComponent != null) {
			slideViewComponent.update(this, getCurrentSlide().orElse(null));
		}
	}

	public void prevSlide() {
		if (currentSlideNumber > 0) {
			setSlideNumber(currentSlideNumber - 1);
		}
	}

	public void nextSlide() {
		if (currentSlideNumber < getSize() - 1) {
			setSlideNumber(currentSlideNumber + 1);
		}
	}

	public void clear() {
		showList.clear();
		setSlideNumber(NO_SLIDE_INDEX);
	}

	public void append(Slide slide) {
		showList.add(slide);
	}

	public Optional<Slide> getSlide(int number) {
		return (number >= 0 && number < getSize()) ? Optional.of(showList.get(number)) : Optional.empty();
	}

	public Optional<Slide> getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}
}
