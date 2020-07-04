public class lec_22_june_PQ{

    
    //Priority Queue Using Heap ////////////////////////////////////////////////////////////////////////
    public static class PriorityQueue {
        ArrayList<Integer> data;
    
        public PriorityQueue() {
          data = new ArrayList<>();
        }
        public void swap(int i,int j){
            int iVal = data.get(i);
            int jVal = data.get(j);
            
            data.set(i,jVal);
            data.set(j,iVal);
        }
        public void upheapify(int ci){
            if(ci == 0){
                return;
            }
            
            int pi = (ci-1)/2;
            
            if(data.get(pi) > data.get(ci)){
                // HOP isnot being honoured
                swap(pi,ci);
                upheapify(pi);
            }
        }
        public void add(int val) {
          // write your code here
          data.add(val);
          upheapify(data.size()-1);
        }
        
        public void downHeapify(int pi){
            int lci = (pi*2) + 1;
            int rci = (pi*2) + 2;
            
            int minIdx = pi;
            if(lci < data.size() && data.get(minIdx) > data.get(lci)){
                minIdx = lci;
            }
            
            if(rci < data.size() && data.get(minIdx) > data.get(rci)){
                minIdx = rci;
            }
            
            if(minIdx != pi){
                
                swap(minIdx,pi);
                
                downHeapify(minIdx);
            }
        }
        public int remove() {
          // write your code here
          if(size() == 0){
              System.out.println("Underflow");
              return -1;
          }
          // swap
          swap(0,data.size()-1);
          // removal
          int HPele = data.remove(data.size()-1);
          
          // downHeapify
          downHeapify(0);
          
          return HPele;
        }
    
        public int peek() {
          // write your code here
          if(size() == 0){
              System.out.println("Underflow");
              return -1;
          }
          // root --> highest priority element --> HOP
          return data.get(0);
        }
    
        public int size() {
          // write your code here
          return data.size();
        }
      }

   
    // merge K sorted list /////////////////////////////////////////////////////////////////////////////////
    public static class Pair implements Comparable<Pair>{
        int li;
        int di;
        int val;
        
        Pair(int li,int di,int val){
            this.li = li;
            this.di = di;
            this.val = val;
        }
        
        public int compareTo(Pair o){
            // return this.val-o.val;
            if(this.val < o.val){
                return -1;
            }else if(this.val > o.val){
                return +1;
            }else{
               return 0;
            }
        }
    }
   public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
      ArrayList<Integer> rv = new ArrayList<>();

      // write your code here
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      
      for(int listIdx = 0 ; listIdx < lists.size();listIdx++){
          ArrayList<Integer> ithList = lists.get(listIdx);
          int val = ithList.get(0);
          pq.add(new Pair(listIdx,0,val));
      }
        
      while(pq.size() > 0){
          Pair minPair = pq.remove();
          
          // add val to result
          rv.add(minPair.val);
          
          minPair.di++;
          
          if(minPair.di < lists.get(minPair.li).size()){
              minPair.val = lists.get(minPair.li).get(minPair.di);
              pq.add(minPair);
          }
          
      }  
      return rv;
   }
   
}
