Android Word Search
===================

What is Android Word Search ?
-----------------------------

An efficient way to store large list of words (10^6 approx.) in Android Operating System with low memory footprint and very fast lookup.

Idea Overview
-------------

Lets say we are developing an Android word game that needs a large array of words.
As the array of words may change, it is preferred to download over the network on first application lauch or whenever the data set is changed.

* As it is a mobile application, using as little memory as possible, thereby requiring only a small initial download for the compressed and encoded input data.

* Fast Lookup for the given query word. Lookups are typically asking "is this word in the array ?"

* The size of word is low, say <= 12.

So, suppose an alphabet of not more than 26 letters, i.e. 5 bits per letter. You can cram then 12 letters (12 x 5 = 60 bits) into a single Java long by using 5 bits/letter trivial encoding.

This means that actually if you don't have longer words than 12 letters / word you can just represent your "array of strings" as a set of Java longs. If you have 250,000 words a trivial presentation of this set as a single, sorted array of longs should take 250,000 words x 8 bytes / word = 2,000,000 ~ 2MB memory. Lookup is then by binary search, which should be very fast given the small size of the data set (less than 20 comparisons as 2^20 takes you to above one million).

If you have longer words than 12 letters, then I would store the >12 letters words in another array where 1 word would be represented by 2 concatenated Java longs in an obvious manner.

The reason why this works and is likely more space-efficient than a trie and at least very simple to implement if that the array of strings is constant. Search trees are good if you need to modify the data set, but if the data set is constant, you can often run a way with simple binary search.