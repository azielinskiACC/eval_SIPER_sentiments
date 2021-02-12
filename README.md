# The Sentiment Analysis for SIPER Evaluation Reports
This repository contains code and data associated with “SIPER Project in ISDEC.” PDF can be found here

If you use any of the code or ideas presented here, please cite our paper:
* TODO

## In a nutshell
By analyzing data from the SIPER repository tracts in Scopus from 2010 - 2018, this paper finds that the Rao Index suffers from high fluctuations that make it an unreliable indicator for science when applied fully automatically. 
The sensitivity of parameter tuning for the underlying LDA model may partly explain divergencies when measrung Rao across different FH and MPG institutes.



## Code
With the provided code the sentiment and confidence can be constructed from the SIPER evaluation reports.

* **LanguageDetectionTIKA.java:** For classification of documents based on n-grams and word sets based on TIKA, a language detection tool for 18 languages. Files are read from a directory and split into subdirecrtory by language. [Link to TIKA] [https://www.tutorialspoint.com/tika/tika_language_detection.htm]
* **concepts_k500_50.R:** Extracts concepts from the structural topic model output, the number of words, topics, and FREX weighing can be adjusted in the code to get at the differend K/FREX scenarios.
* **proquest-skipgrams.py:** Code to learn the concept embeddings to find out which are distal or proximal linkages.
* **obtain_innovation_years_proquest_msearch_chunks_only_hits_filtered.py:** Looks up each dyad and dumps when it was first introduced, introduced thesis ID and future uptakes for that dyad.
* **merge_innovations_uptakes_files.py:** Aggregates uptakes etc. by thesis ID.
* **merge_innovations_uptakes_files.py:** Runs Structural Topic Models at specified range of K (50-1000 in the paper).

## Logic for computing novelty and uptake
We seek to compute the number of uptakes for each dyad and then aggregate across a document. To find the number of uptakes of a concept dyad, we first find the first theses in which the dyad was introduced which in turn needs us to identify all theses that used the link and then obtain the thesis that is the earliest as the introducing thesis (by graduation year). The rest of the dyads are the future uptakes. This logic an be implemented in many ways based on your needs. Below we point to one such implementation that was suited for compute infrastructure. Note that the below implementation by its design requires customization because of the heavy setup needed. We chose this approach because envisioned future projects that needed this anyways where we needed efficient ways of identifying theses that contained a given set of terms.

We implement the above by building a search engine that returns documents (theses) that contain a term. In particular, we query the search engine (Elastic Search) for each dyad and obtain number of documents/theses that are hits (i.e., that contain the dyad) as well as their publication years sorted in ascending order by year. The first is the thesis that introduced the link (dyad) (ties are broken arbitrarily) and the rest of hits correspond to the total number of uptakes for that dyad. The idea is to store each thesis (document) in Elastic search along with meta data (like graduation year, identifier, etc.). Then we can efficiently use Elastic Search’s functionality to retrieve theses that contain a given dyad: obtain_innovation_years_proquest_msearch_chunks_only_hits_filtered.py. We had to do this in chunks because of restricted memory/compute requirements. The outputs of these are then just aggregated across each thesis to obtain (a) The number of links (b) the total uptakes of dyads and the (c) mean distal score introduced by the thesis.

## Data
For the concepts extracted for the K = 500 LDA Topic Model where we equally balance frequency and exclusivity (which we extract in concepts_k500_50.R), please see k500_wordcouds_n_to_n.zip for visualizations or frexconcepts_k500_50.rda for the data (second element in the list).

### For raw data 
* SIPER Policy Evaluation Database: https://www.risis2.eu/2019/08/26/siper-worldwide-policy-evaluation-database/

Figure 2.
!(https://github.com/azielinskiACC/eval_SIPER_sentiments/blob/main/SiperLanguagesData.png)

### Research Institutes:
* [Link to Fraunhofer Institutes] []
* [Link to MPG Institutes] ()

[Link to Fraunhofer Institutes](https://en.wikipedia.org/wiki/Fraunhofer_Society#:~:text=%20These%20are%20Fraunhofer%20Institute%20s%20for:%20,IZI%2010%20Laser%20Technology%20%E2%80%93%20ILT%20More)

[Link to MPG Institutes](https://en.wikipedia.org/wiki/List_of_Max_Planck_Institutes#:~:text=Institutes%20and%20Research%20Units%20%20%20%20Name,&%20Social%20Sciences%20%2017%20more%20rows)


[You can use numbers for reference-style link definitions][1]

Or leave it empty and use the [link text itself].

