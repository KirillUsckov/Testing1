package modalWindows.enums;

public enum ModalWindowsButtons {
    JS_ALERT_BUTTON("jsAlert"), JS_PROMPT_BUTTON("jsPrompt"),JS_CONFIRM_BUTTON("jsConfirm");

    private String button;

    ModalWindowsButtons(String button){
        this.button = button;
    }

    public String getButton(){ return button;}
}
