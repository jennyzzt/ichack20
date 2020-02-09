package com.example.someapp.ui.tabs;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class PageViewModel extends ViewModel {

    private Map<String, Integer> mItems = new HashMap<>();
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private static final String NUM_SPACES = "                  ";

    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
        if (input == 1) {
            // ingredients tab
            return ingredientsToString(mItems);
        } else if (input == 2) {
            // equipment tab
            return equipmentsToString(mItems);
        } else if (input == 3) {
            // other factors tab
            return factorsToString(mItems);
        }
        // should not reach here
        return "New feature to be added soon at Section: " + input + "!";
        }
    });

    public void addItem(String item) {
        mItems.put(item, (mItems.containsKey(item)) ? mItems.get(item) + 1 : 1);
        updateLiveText();
    }

    private void updateLiveText() {
        System.out.println("updateLiveText called. before: " + mIndex.getValue());
        mText = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                System.out.println("switching to tab " + input);
            if (input == 1) {
                // ingredients tab
                return ingredientsToString(mItems);
            } else if (input == 2) {
                // equipment tab
                return equipmentsToString(mItems);
            } else if (input == 3) {
                // other factors tab
                return factorsToString(mItems);
            }
            return "Section: " + input + " under progress";
            }
        });
        Integer val = mIndex.getValue();
        mIndex.setValue(val + 1);
        mIndex.setValue(val);
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return this.mText;
    }

    public Map<String, Integer> getItems() {
        return mItems;
    }

    private String ingredientsToString(Map<String, Integer> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ingredient" + NUM_SPACES);
        sb.append("Quantity");

        sb.append('\n');
        sb.append('\n');
        for (String key : items.keySet()) {
            sb.append(key + NUM_SPACES);
            sb.append(items.get(key));
            sb.append('\n');
        }

        return sb.toString();
    }

    private String equipmentsToString(Map<String, Integer> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipment" + NUM_SPACES);
        sb.append("Quantity");

        sb.append('\n');
        sb.append('\n');
        for (String key : items.keySet()) {
            sb.append('\n');
            sb.append(key + NUM_SPACES);
            sb.append(items.get(key));
        }

        return sb.toString();
    }

    private String factorsToString(Map<String, Integer> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("Factor" + NUM_SPACES);
        sb.append("Given Value");

        sb.append('\n');
        sb.append('\n');
        for (String key : items.keySet()) {
            sb.append(key + NUM_SPACES);
            sb.append(items.get(key));
            sb.append('\n');
        }

        return sb.toString();
    }

}