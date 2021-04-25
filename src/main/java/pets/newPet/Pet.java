package pets.newPet;

import java.util.List;

public class Pet {
    private List<String> photoUrls;
    private String name;
    private int id;
    private Category category;
    private List<TagsItem> tags;
    private String status;

    public final void setPhotoUrls(final List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public final List<String> getPhotoUrls() {
        return photoUrls;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getId() {
        return id;
    }

    public final void setCategory(final Category category) {
        this.category = category;
    }

    public final Category getCategory() {
        return category;
    }

    public final void setTags(final List<TagsItem> tags) {
        this.tags = tags;
    }

    public final List<TagsItem> getTags() {
        return tags;
    }

    public final void setStatus(final String status) {
        this.status = status;
    }

    public final String getStatus() {
        return status;
    }
}