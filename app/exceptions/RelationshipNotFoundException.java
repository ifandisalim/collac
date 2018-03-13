package exceptions;

public class RelationshipNotFoundException extends Exception {

    private Integer resourceId;
    private Integer relationedResourceId;

    public RelationshipNotFoundException(Integer resourceId, Integer relationedResourceId) {
        super("Resource with id " + resourceId + " does not have relationship with resource: " + relationedResourceId);
        this.resourceId = resourceId;
        this.relationedResourceId = relationedResourceId;

    }

    public Integer getResourceId() {
        return resourceId;
    }

}
