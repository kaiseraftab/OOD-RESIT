class DemoPresentation implements Accessor {
    @Override
    public void loadFile(Presentation presentation, String unusedFilename) {
        presentation.setTitle("Demo Presentation");

        addSlide(presentation, "JabberPoint",
                new String[] {
                        "The Java presentation tool",
                        "Copyright (c) 1996-2000: Ian Darwin",
                        "Copyright (c) 2000-now: Gert Florijn and Sylvia Stuurman",
                        "Calling Jabberpoint without a filename will show this presentation",
                        "Navigate:",
                        "Next slide: PgDn or Enter",
                        "Previous slide: PgUp or up-arrow",
                        "Quit: q or Q"
                });

        addSlide(presentation, "Demonstration of levels and styles",
                new String[] {
                        "Level 1",
                        "Level 2",
                        "Again level 1",
                        "Level 1 has style number 1",
                        "Level 2 has style number 2",
                        "This is how level 3 looks like",
                        "And this is level 4"
                });

        Slide thirdSlide = new Slide();
        thirdSlide.setTitle("The third slide");
        thirdSlide.append(1, "To open a new presentation,");
        thirdSlide.append(2, "use File->Open from the menu.");
        thirdSlide.append(1, " ");
        thirdSlide.append(1, "This is the end of the presentation.");
        thirdSlide.append(new BitmapItem(1, "JabberPoint.jpg"));
        presentation.append(thirdSlide);
    }

    @Override
    public void saveFile(Presentation presentation, String unusedFilename) {
        throw new IllegalStateException("Save operation is not supported for the demo presentation.");
    }

    private void addSlide(Presentation presentation, String title, String[] content) {
        Slide slide = new Slide();
        slide.setTitle(title);
        for (int i = 0; i < content.length; i++) {
            slide.append(i + 1, content[i]);
        }
        presentation.append(slide);
    }
}