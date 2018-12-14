package lidar;

import lib.Constants;
import icp.Point;
import lib.util.Logger;
import java.text.NumberFormat;

import java.util.ArrayList;

/**
 * Holds a single 360 degree scan from the lidar.  The timestamp
 * for the scan is that of the first point.
 */
class LidarScan 
{
    private ArrayList<Point> mPoints = new ArrayList<>(Constants.kLidarScanSize);
    private double mTimestamp = 0;

    public LidarScan()
    {
    }

    public String toJsonString() 
    {
        String json = "{\"class\":\"lidarscan\"" + 
                    ", \"timestamp\": " + mTimestamp + 
                    ", \"ptsXY\": [";
        int i = 0;
        NumberFormat fmt = NumberFormat.getInstance();
        for (Point point : mPoints)
        {
            // json += "{\"x\":" + point.x + ", \"y\":" + point.y + "},";
            if(i++ > 0)
                json += ",";
            json += String.format("[%.3f,%.3f]", point.x, point.y);
        }
        json += "]}";
        return json;
    }

    public String toString()
    {
        String s = "";
        for (Point point : mPoints) 
        {
            s += "x: " + point.x + ", y: " + point.y + "\n";
        }
        return s;
    }

    public ArrayList<Point> getPoints()
    {
        return mPoints;
    }

    public double getTimestamp()
    {
        return mTimestamp;
    }

    public void addPoint(Point point, double time)
    {
        if (mTimestamp == 0)
            mTimestamp = time;
        mPoints.add(point);
    }
}
