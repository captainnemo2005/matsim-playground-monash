package model;

public interface DiscreteModeChoiceModel {


    static public enum FallbackBehaviour {
        IGNORE_AGENT, INITIAL_CHOICE, EXCEPTION
    }


    static public class NoFeasibleChoiceException extends Exception {
        private static final long serialVersionUID = -7909941248706791794L;

        public NoFeasibleChoiceException(String message) {
            super(message);
        }
    }
}
