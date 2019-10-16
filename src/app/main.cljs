(ns app.main
  (:require ["@blueprintjs/core" :as bp]
            [reagent.core :as r]))

;; Util functions

(defn log [category content]
  (println category (js/Date.) content))

(defn console-greeting []
  (log "[greeting]" "it works!"))

(defonce dialog-open
  (r/atom false))

(defn toggle-dialog [event]
  (swap! dialog-open false?)) ;; negates the current boolean value

;; App

(defn example-app []
  [:<>
   [:> bp/Button {:icon "eye-open" :text "Print greeting in console!" :onClick console-greeting}]
   [:br][:br]
   [:> bp/Button {:icon "chat" :text "Show dialog" :onClick toggle-dialog}]

   ;; Dialog
   [:> bp/Dialog {:icon "info-sign" :isOpen @dialog-open :title "Title here" :onClose toggle-dialog}
    [:div {:className bp/Classes.DIALOG_BODY}
     [:p
      [:strong "bla bla blah"]]
     [:p "bla bla bla continues"]]
    [:div {:className bp/Classes.DIALOG_FOOTER}
     [:div {:className bp/Classes.DIALOG_FOOTER_ACTIONS}
      [:> bp/Tooltip {:content "This button is hooked up to close the dialog."}
       [:> bp/Button {:onClick toggle-dialog} "Close"]]
      [:> bp/AnchorButton {:intent bp/Intent.PRIMARY :href "https://github.com" :target "_blank"} "Visit Github"]
       ]]]
   ])

;; App initialization

(defn mount-root []
  (r/render [example-app] (.getElementById js/document "app")))

(defn main! []
  (mount-root)
  (println "[core]: loading"))

(defn reload! []
  (mount-root)
  (println "[core] reloaded"))
