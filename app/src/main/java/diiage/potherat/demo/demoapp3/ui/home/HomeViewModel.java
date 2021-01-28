package diiage.potherat.demo.demoapp3.ui.home;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.model.Quote;

public class HomeViewModel extends ViewModel {

    private QuoteRepository quoteRepository;

    @Inject
    @ViewModelInject
    public HomeViewModel(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public LiveData<String> getCountQuote() {
        return Transformations.map(quoteRepository.getCountQuote(), quote ->{
            return quote.toString();
        });
         // return quoteRepository.getCountQuote();
    }

    public LiveData<String> getDistinctSource() {
        return Transformations.map(quoteRepository.getDistinctSource(), source ->{
            return source.toString();
        });
        // return quoteRepository.getDistinctSource();
    }

    public LiveData<Quote> getLastQuote() {
        return quoteRepository.getLastQuote();
    }
}