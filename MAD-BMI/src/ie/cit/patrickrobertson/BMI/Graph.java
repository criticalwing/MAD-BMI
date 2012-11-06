package ie.cit.patrickrobertson.BMI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Graph extends View {

	ResultsManager rm;
	Paint paintGrey, paintWhite;
	int xSpacing, ySpacing;

	// setup details and work out ranges
	public Graph(Context context, ResultsManager rm, int size) {
		super(context);
		this.rm = rm;
		paintGrey = new Paint();
		paintWhite = new Paint();
		this.ySpacing = size / getMaxValue();
		this.xSpacing = size / (rm.getResults().size());
	}

	public void onDraw(Canvas canvas) {
		paintGrey.setColor(android.graphics.Color.GRAY);
		paintGrey.setStrokeWidth(5);
		paintWhite.setColor(android.graphics.Color.WHITE);
		int position = 10;
		/*canvas.drawLine(xSpacing*1, 29*ySpacing,xSpacing*2, 26*ySpacing, paintGrey);
		canvas.drawLine(xSpacing*2, 26*ySpacing,xSpacing*3, 25*ySpacing, paintGrey);
		canvas.drawLine(xSpacing*3, 25*ySpacing,xSpacing*4, 15*ySpacing, paintGrey);*/
		
		for (Result result : rm.getResults()) {
			if (rm.getResults().indexOf(result) == (rm.getResults().size()) - 1) {
			} else {
				// get details of this result
				int startYCoord = result.getBMI() * ySpacing;
				int startXCoord = position;
				// get next result in line
				int endYCoord = rm.getResults()
						.get(rm.getResults().indexOf(result) + 1).getBMI()*ySpacing;
				int endXCoord = position + xSpacing;
				// drawline
				canvas.drawLine(startXCoord, startYCoord, endXCoord, endYCoord,
						paintGrey);
				// add value to take to next position
				position += xSpacing;
			}
		}
		//draw Labels
		int yStart=0;
		for(int x=0;x<getMaxValue();x+=10){
			
			canvas.drawText(String.valueOf(x), 0,yStart, paintWhite);
			yStart+=(ySpacing*x);
		}

	}

	private int getMaxValue() {
		List<Integer> temp = new ArrayList<Integer>();
		for (Result x : rm.getResults()) {
			temp.add(x.getBMI());
		}
		Collections.sort(temp);
		return temp.get(rm.getResults().size() - 1);
	}

}