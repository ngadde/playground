package com.logic;

public class LogicCheck {
    public void test(){
    	double firstPoint=2.4;
    	double lastPoint=10.4;
    	for(;;){
    		//
    		double first=2.5;
    		double last=3.4;
    		boolean identifyStartingSgment=false;
    		if(!identifyStartingSgment){
    			if(firstPoint==first && lastPoint==last){
    				// add segment
    				break;
    			}
	    		if(firstPoint < last ){
	    			//add segment;
	    			//my last point less than last segment or equal to last segment
	    			if(lastPoint < last || lastPoint==last){
	    				break;
	    			}
	    			//add segment;
	    			//this starting segment
	    			identifyStartingSgment=true;
	    		}else{
	    			//continue to find first segment
	    			continue;
	    		}
    		}
    		
    		if(  lastPoint <= last){
    			//add segment
    			//this is end segment
    		}else if(last < lastPoint){
    			//add segment
    		}
    	}
    }
}
