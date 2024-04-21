import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KeyController extends KeyAdapter {
	private static final int KEY_NEXT_SLIDE1 = KeyEvent.VK_PAGE_DOWN;
	private static final int KEY_NEXT_SLIDE2 = KeyEvent.VK_DOWN;
	private static final int KEY_NEXT_SLIDE3 = KeyEvent.VK_ENTER;
	private static final char KEY_NEXT_SLIDE4 = '+';

	private static final int KEY_PREV_SLIDE1 = KeyEvent.VK_PAGE_UP;
	private static final int KEY_PREV_SLIDE2 = KeyEvent.VK_UP;
	private static final char KEY_PREV_SLIDE3 = '-';

	private static final char KEY_QUIT1 = 'q';
	private static final char KEY_QUIT2 = 'Q';

	private final Presentation presentation; // Commands are given to the presentation

	public KeyController(Presentation presentation) {
		this.presentation = presentation;
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();
		char keyChar = keyEvent.getKeyChar();
		if (isNextSlideKey(keyCode, keyChar)) {
			presentation.nextSlide();
		} else if (isPreviousSlideKey(keyCode, keyChar)) {
			presentation.prevSlide();
		} else if (isQuitKey(keyChar)) {
			quitApplication();
		}
	}

	private boolean isNextSlideKey(int keyCode, char keyChar) {
		return keyCode == KEY_NEXT_SLIDE1 || keyCode == KEY_NEXT_SLIDE2 || keyCode == KEY_NEXT_SLIDE3 || keyChar == KEY_NEXT_SLIDE4;
	}

	private boolean isPreviousSlideKey(int keyCode, char keyChar) {
		return keyCode == KEY_PREV_SLIDE1 || keyCode == KEY_PREV_SLIDE2 || keyChar == KEY_PREV_SLIDE3;
	}

	private boolean isQuitKey(char keyChar) {
		return keyChar == KEY_QUIT1 || keyChar == KEY_QUIT2;
	}

	private void quitApplication() {
		// Potentially save state or prompt user before exit
		System.exit(0);
	}
}
