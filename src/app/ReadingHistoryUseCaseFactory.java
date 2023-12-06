package app;

import interface_adapter.ViewManagerModel;
import user_manage.data_access.FileHistoryDataAccessObject;
import user_manage.entity.HistoryFactory;

import user_manage.service.history.read_history.ReadingHistoryInteractor;
import user_manage.service.history.read_history.ReadingHistoryOutputBoundary;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryPresenter;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
import view.ReadingHistoryView;

import javax.swing.*;
import java.io.IOException;

public class ReadingHistoryUseCaseFactory {
    private ReadingHistoryUseCaseFactory(){}

    public static ReadingHistoryView create(ViewManagerModel viewManagerModel, ReadingHistoryViewModel readingHistoryViewModel, FileHistoryDataAccessObject readingDataAccessObject) {
        try {
            ReadingHistoryController readingHistoryController = createReadingHistoryUseCase(readingHistoryViewModel, viewManagerModel, readingDataAccessObject);
            //AddingHistoryController addingHistoryController = createAddingHistoryUseCase(addingHistoryViewModel, viewManagerModel,readingDataAccessObject);
            return new ReadingHistoryView(readingHistoryViewModel, readingHistoryController, viewManagerModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open reading history data file.");
        }
        return null;
    }

    /*private static AddingHistoryController createAddingHistoryUseCase(AddingHistoryViewModel addingHistoryViewModel, ViewManagerModel viewManagerModel, FileHistoryDataAccessObject addinghistoryobj)
        throws IOException{
        AddingHistoryOutputBoundary addingHistoryPresenter = new AddingHistoryPresenter(viewManagerModel, addingHistoryViewModel);
        AddingHistoryInteractor addingHistoryInteractor = new AddingHistoryInteractor(addingHistoryPresenter, addinghistoryobj);

        return new AddingHistoryController((addingHistoryInteractor));
    }

     */


    private static ReadingHistoryController createReadingHistoryUseCase(ReadingHistoryViewModel readingHistoryViewModel,
                                                                        ViewManagerModel viewManagerModel,
                                                                        FileHistoryDataAccessObject readingDataAccessObject) throws IOException {
        ReadingHistoryOutputBoundary readingHistoryPresenter = new ReadingHistoryPresenter(readingHistoryViewModel, viewManagerModel);
        ReadingHistoryInteractor readingHistoryInteractor = new ReadingHistoryInteractor(readingHistoryPresenter,
                readingDataAccessObject);

        return new ReadingHistoryController(readingHistoryInteractor);
    }
}