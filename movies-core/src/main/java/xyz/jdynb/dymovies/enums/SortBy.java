   package xyz.jdynb.dymovies.enums;

   import lombok.Getter;

   /**
    * 排序配置
    */
   @Getter
   public enum SortBy {
       /**
        * 按照最新的更新时间排序
        */
       LATEST(1),
       /**
        * 按照最老的更新时间排序
        */
       OLDEST(2);

       private final int value;

       SortBy(int value) {
           this.value = value;
       }

       public static SortBy fromValue(int value) {
           for (SortBy sortBy : SortBy.values()) {
               if (sortBy.getValue() == value) {
                   return sortBy;
               }
           }
           throw new IllegalArgumentException("No constant with value " + value + " found");
       }
   }
   