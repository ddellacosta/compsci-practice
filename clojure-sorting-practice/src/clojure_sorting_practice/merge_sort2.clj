(ns clojure-sorting-practice.merge-sort2)

(defn merge [a b]
  (loop [a a b b merged []]
;;    (println "a: " a " , b: " b " , merged: " merged)
    (cond
     (and (not (seq a)) (not (seq b)))
     merged

     (not (seq a))
     (apply conj merged b)

     (not (seq b))
     (apply conj merged a)

     :else
     (if (< (first a) (first b))
       (recur (rest a) b (conj merged (first a)))
       (recur a (rest b) (conj merged (first b)))))))

(defn merge-sort
  [a]
  (if (< (count a) 2)
    a
    (let [[a' b'] (split-at (/ (count a) 2) a)]
      (merge (merge-sort a') (merge-sort b')))))

(comment
  (time (merge-sort (take 100 (repeatedly #(rand-int 100)))))
)
