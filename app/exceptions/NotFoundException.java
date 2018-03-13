package exceptions;

public class NotFoundException extends Exception {

    private Integer resourceId;

    public NotFoundException(Integer resourceId) {
        super("Resource with id " + resourceId + " is not found.");
        this.resourceId = resourceId;

    }

    public Integer getResourceId() {
        return resourceId;
    }

}
