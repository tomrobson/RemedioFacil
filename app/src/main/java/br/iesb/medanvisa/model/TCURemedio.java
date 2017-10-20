package br.iesb.medanvisa.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tomfarias on 20/10/17.
 */

public interface TCURemedio {
    @GET("rest/remedios")
    Call<List<Remedio>> listarRemedios();
}
