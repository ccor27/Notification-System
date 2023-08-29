package model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Category extends Observable {

    private CategoryType categoryType;

    public Category(){}
    public Category(CategoryType categoryType) {
        this.categoryType=categoryType;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
    public void notify(String message){
        this.setChanged();
        this.notifyObservers(message);
        this.clearChanged();
    }
}
