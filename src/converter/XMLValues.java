package converter;

public class XMLValues {
	double Score;
	double ViewCount;
	String Tags;
	String Date;

	public double getScore() {
		return Score;
	}

	public void setScore(double score) {
		Score = score;
	}

	public double getViewCount() {
		return ViewCount;
	}

	public void setViewCount(double viewCount) {
		ViewCount = viewCount;
	}

	public String getTags() {
		if(Tags==null)
		{
			return "";
		}
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

}
