(ns app.main
  (:require ["@blueprintjs/core" :as bp]
            [reagent.core :as r]))

;; Util functions

(defn console-greeting []
  (println "[greeting]" (js/Date.) "it works!"))

(defonce dialog-open
  (r/atom false))

;; App

(defn example-app []
  [:<>
   [:> bp/Button {:icon "eye-open" :text "Print greeting in console!" :onClick console-greeting}]
   [:br][:br]
   [:> bp/Button {:icon "chat" :text "Show dialog" :onClick #(swap! dialog-open false?)}]

   ;; Dialog
   [:> bp/Dialog {:icon "info-sign" :isOpen @dialog-open :title "Title here" :onClose #(reset! dialog-open false)}
    [:div {:className bp/Classes.DIALOG_BODY}
     [:p
      [:strong "bla bla blah"]]
     [:p "bla bla bla continues"]]
    [:div {:className bp/Classes.DIALOG_FOOTER}
     [:div {:className bp/Classes.DIALOG_FOOTER_ACTIONS}
      [:> bp/Tooltip {:content "This button is hooked up to close the dialog."}
       [:> bp/Button {:onClick #(reset! dialog-open false)} "Close"]]]]]
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
