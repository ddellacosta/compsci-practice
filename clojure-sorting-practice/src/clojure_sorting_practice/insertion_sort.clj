(ns clojure-sorting-practice.insertion-sort)

(defn add-to-arraylist-at-index
  [al add idx]
  (if (>= (.size al) (inc idx))
    (.set al idx add)
    (.add al idx add)))

(defn arraylist-insertion-sort
  [unsorted]
  (let [AA (java.util.ArrayList. [(first unsorted) (second unsorted)])]
    (loop [key (second unsorted)
           j   1
           A   (rest (rest unsorted))]
      (if-not key
        AA
        (do
          (loop [i (dec j)]
            (if-not (and (>= i 0)
                         (> (.get AA i) key))
              (add-to-arraylist-at-index AA key (inc i))
              (do (add-to-arraylist-at-index AA (.get AA i) (inc i))
                  (recur (dec i)))))
          (recur (first A) (inc j) (rest A)))))))

(defn seq-insertion-sort
  [unsorted]
  (loop [key (first (rest unsorted))
         ns  (rest (rest unsorted))
         A   [(first unsorted)]]
    (if-not key
      A
      (recur
       (first ns) (rest ns)
       (loop [a        (last A)
              A-before (butlast A)
              A-after  []]
         (if (or (not a) (< a key))
           (if a
             (concat A-before [a key] A-after)
             (concat A-before [key] A-after))
           (recur
            (last A-before)
            (butlast A-before)
            (if (seq A-after) (into [a] A-after) [a]))))))))
