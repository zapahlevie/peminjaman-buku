package pens.ac.id;

import pens.ac.id.service.ServiceBuku;
import pens.ac.id.service.ServiceMahasiswa;
import pens.ac.id.service.ServicePeminjaman;
import pens.ac.id.service.ServiceUser;

public class AppContainer {

    private static AppContainer instance = null;

    public AppContainer() {
        init();
    }

    public static AppContainer getInstance() {
        return AppContainerHolder.INSTANCE;
    }

    private static class AppContainerHolder {

        private static final AppContainer INSTANCE = new AppContainer();
    }

    private ServiceMahasiswa serviceMahasiswa;
    private ServiceUser serviceUser;
    private ServiceBuku serviceBuku;
    private ServicePeminjaman servicePeminjaman;

    private void init() {
        serviceMahasiswa = new ServiceMahasiswa();
        serviceUser = new ServiceUser();
        serviceBuku = new ServiceBuku();
        servicePeminjaman = new ServicePeminjaman();

    }

    public ServiceMahasiswa getServiceMahasiswa() {
        return serviceMahasiswa;
    }

    public ServiceUser getServiceUser() {
        return serviceUser;
    }

    public ServiceBuku getServiceBuku() {
        return serviceBuku;
    }

    public ServicePeminjaman getServicePeminjaman() {
        return servicePeminjaman;
    }
}
