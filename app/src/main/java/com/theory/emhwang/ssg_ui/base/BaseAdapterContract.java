package com.theory.emhwang.ssg_ui.base;

import java.util.List;

public interface BaseAdapterContract {
    interface View {

        void notifyDataAdapter();
    }

    interface Model<T> {

        void addItems(final List<T> list);

        void clearAll();

        T getItems(final int index);
    }
}
