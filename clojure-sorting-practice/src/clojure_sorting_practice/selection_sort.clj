(ns clojure-sorting-practice.selection-sort)

(defn select-smallest
  [unsorted]
  (reduce
   #(if (:s %1)
      (if (< %2 (:s %1))
        {:s %2
         :r (conj (:r %1) (:s %1))}
        (update-in %1 [:r] (fn [prev] (conj prev %2))))
      (assoc %1 :s %2))
   {:s nil :r []}
   unsorted))

(defn selection-sort
  [unsorted]
  (if (= (count unsorted) 1)
    unsorted
    (let [{:keys [s r]} (select-smallest unsorted)]
      (concat [s] (selection-sort r)))))
