package com.fpx.exception;

public class PluginInitialisationException extends Exception {
    public enum PluginInitialisationError {
        PLUGIN_LIFECYCLE("Plugin lifecycle has ended"), 
        ILLEGAL_ACCESS("Illegal access while creating the plugin instance"), 
        INSTANTIATION("Error while creating the plugin instance"), CLASS_CAST("Plugin class cannot be cast"), 
        CLASS_NOT_FOUND("Plugin class not found");
        private String message;

        private PluginInitialisationError(String message) {
            this.message = message;
        }
    }

    private PluginInitialisationError errorType;

    public PluginInitialisationException(PluginInitialisationError errorType) {
        this.errorType = errorType;
    }

    public PluginInitialisationException(PluginInitialisationError errorType, Exception cause) {
        super(errorType.message);
        this.errorType = errorType;
        this.initCause(cause);
    }

    public String getErrorMessage() {
        return errorType.message;
    }
}