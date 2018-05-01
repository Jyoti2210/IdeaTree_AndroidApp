package mj.jyoti.com.groovewithmj;

public class musicData {
    private String image;
    private String trackName;
    private String artistName;
    private String trackPrice;

    public String getImage(){
        return image;
    }

    public String getName() {
        return trackName;
    }

    public String getArtistName(){
        return artistName;
    }

    public String getTrackPrice(){
        return trackPrice;
    }

    public musicData(String image, String name, String artist, String price){
        this.image = image;
        this.trackName = name;
        this.artistName = artist;
        this.trackPrice = price;
    }
}
