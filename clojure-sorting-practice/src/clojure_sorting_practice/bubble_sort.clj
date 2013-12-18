(ns clojure-sorting-practice.bubble-sort)

(defn bubble-down
  [i unsorted]
  (loop [j (dec (count unsorted))
         sorted unsorted]
    (if (< j (inc i))
      sorted
      (recur
       (dec j)
       (if (< (nth sorted j)
              (nth sorted (dec j)))
         (into [] (concat
                   (subvec sorted 0 (dec j))
                   [(nth sorted j)]
                   [(nth sorted (dec j))]
                   (subvec sorted (inc j) (count sorted))))
         sorted)))))

;; Naive, doesn't check to see if we are sorted at any point
(defn bubble-sort
  [unsorted]
  (loop [i 0 sorted unsorted]
    (if (= i (count unsorted))
      sorted
      (recur (inc i) (bubble-down i sorted)))))
